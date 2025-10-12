package com.hrapp.hr_payroll.integration;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.listener.EmployeeCreatedListener;
import com.hrapp.hr_payroll.service.PayrollService;
import com.hrapp.hr_payroll.testconfig.TestPayrollConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@EmbeddedKafka(partitions = 1, topics = { "employee.created" })
@DirtiesContext
@EnableKafka
@Import(TestPayrollConfig.class)
public class KafkaIntegrationTest {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    private EmployeeCreatedListener employeeCreatedListener;

    private String validMessage;

    @BeforeEach
    public void setup() {
        reset(payrollService);
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafka);
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(producerProps, new StringSerializer(), new StringSerializer())
        );

        validMessage = "{\"employeeId\":101,\"salary\":6500.00,\"payDate\":\"2025-10-09\"}";
        kafkaTemplate.send("employee.created", validMessage);
        kafkaTemplate.flush();
    }

    @Test
    public void testKafkaMessageConsumption() {
        await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    ArgumentCaptor<PayrollRecordDTO> captor = ArgumentCaptor.forClass(PayrollRecordDTO.class);
                    verify(payrollService, times(1)).save(captor.capture());

                    PayrollRecordDTO dto = captor.getValue();
                    assertEquals(101L, dto.getEmployeeId());
                    assertEquals(0, dto.getSalary().compareTo(BigDecimal.valueOf(6500.00)));
                    assertEquals(LocalDate.parse("2025-10-09"), dto.getPayDate());
                });
    }


    @Test
    public void testHandleEmployeeCreatedDirectly() throws Exception {
        employeeCreatedListener.handleEmployeeCreated(validMessage);

        ArgumentCaptor<PayrollRecordDTO> captor = ArgumentCaptor.forClass(PayrollRecordDTO.class);
        verify(payrollService, times(1)).save(captor.capture());

        PayrollRecordDTO dto = captor.getValue();
        assertEquals(101L, dto.getEmployeeId());
        assertEquals(0, dto.getSalary().compareTo(BigDecimal.valueOf(6500.00)));
        assertEquals(LocalDate.parse("2025-10-09"), dto.getPayDate());
    }

    @Test
    public void testWiring() {
        assertNotNull(payrollService);
        assertNotNull(employeeCreatedListener);
    }

}