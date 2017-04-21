package uo.asw.util;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

import es.uniovi.asw.util.DateUtil;

public class DateUtilTest {

    @Test
    public void asDateTest() {
	LocalDate localDate = LocalDate.now();
	Date dateEsperado = Date.from(localDate.atStartOfDay()
		.atZone(ZoneId.systemDefault()).toInstant());
	assertEquals(dateEsperado, DateUtil.asDate(localDate));

	LocalDateTime localDateTime = LocalDateTime.now();
	Date dateEsperado2 = Date
		.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	assertEquals(dateEsperado2, DateUtil.asDate(localDateTime));

    }

    @Test
    public void asLocalDateTest() {
	Date date = new Date();
	LocalDate localDateEsperado = Instant.ofEpochMilli(date.getTime())
		.atZone(ZoneId.systemDefault()).toLocalDate();
	assertEquals(localDateEsperado, DateUtil.asLocalDate(date));
    }

    @Test
    public void asLocalDateTimeTest() {
	Date date = new Date();
	LocalDateTime localDateTimeEsperado = Instant
		.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault())
		.toLocalDateTime();
	assertEquals(localDateTimeEsperado, DateUtil.asLocalDateTime(date));

    }

}
