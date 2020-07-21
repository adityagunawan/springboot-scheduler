package com.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * fixedRate = waktu eksekusi
     * method ini di eksekusi tiap 2 detik sekali
     */
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    /**
     * fixedDelay berarti waktu jeda tiap method selesai dieksekusi
     */
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * fixedRate = waktu eksekusi, 2000 yg berarti method dieksekusi tiap 2 detik sekali
     * initialDelay = waktu jeda tiap eksekusi, 5000 yg berarti waktu jeda 5 detik
     * dengan begitu berarti method ini akan di eksekusi tiap 2 + 5 detik
     */
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    /**
     * cron digunakan untuk menetapkan schedule dengan pola waktu tertentu
     * [detik]  [menit]  [jam]  [hari]  [bulan]  [tahun]
     */
    @Scheduled(cron = "0 40 12 * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

}
