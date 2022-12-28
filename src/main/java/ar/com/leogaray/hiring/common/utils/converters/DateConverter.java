package ar.com.leogaray.hiring.common.utils.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class DateConverter {
	private DateConverter() {
	}

	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	/**
	 * Convierte una fecha en un <code>String</code> a <code>Date</code>.
	 *
	 * @param date   la fecha a convertir.
	 * @param format el formato de la fecha.
	 * @return la fecha como <code>Date</code>.
	 * @throws ParseException
	 */
	public static java.util.Date convertToDateIsoFormat(String date) throws ParseException {

		return convertToDate(date, YYYY_MM_DD_HH);

	}

	/**
	 * Convierte una fecha en un <code>String</code> a <code>Date</code>.
	 *
	 * @param date   la fecha a convertir.
	 * @param format el formato de la fecha.
	 * @return la fecha como <code>Date</code>.
	 * @throws ParseException
	 */
	public static java.util.Date convertToDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);

	}

	/**
	 * Convierte un <code>LocalDateTime</code> a <code>java.util.Date</code>.
	 *
	 * @param localDateTime la fecha como <code>LocalDateTime</code>.
	 * @return la fecha como <code>java.util.Date</code>.
	 */
	public static java.util.Date convertToDate(LocalDateTime localDateTime) {
		return java.sql.Date.from(localDateTime.atZone(ZoneId.of("America/Buenos_Aires")).toInstant());
	}

	/**
	 * Convierte un <code>LocalDate</code> a <code>java.util.Date</code>.
	 *
	 * @param localDate la fecha como <code>LocalDate</code>.
	 * @return la fecha como <code>java.util.Date</code>.
	 */
	public static java.util.Date convertToDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return java.sql.Date.valueOf(localDate);
	}

	/**
	 * Convierte un <code>Date</code> a <code>LocalDate</code>.
	 *
	 * @param date la fecha como <code>Date</code>.
	 * @return la fecha como <code>LocalDate</code>.
	 */
	public static LocalDate convertToLocalDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		if (date instanceof java.sql.Date) {
			return ((java.sql.Date) date).toLocalDate();
		}
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(getTimeZoneID(date)));
		return ldt.toLocalDate();
	}

	public static LocalDateTime convertToLocalDateTime(Timestamp timeStamp) {
		if (timeStamp == null) {
			return null;
		}
		return timeStamp.toLocalDateTime();
	}

	/**
	 * Convierte un <code>Date</code> a <code>LocalDateTime</code>.
	 *
	 * @param date la fecha como <code>Date</code>.
	 * @return la fecha como <code>LocalDateTime</code>.
	 */
	public static LocalDateTime convertToLocalDateTime(java.util.Date date) {
		LocalDateTime ldt = null;
		if (date instanceof java.sql.Date) {
			date = new java.util.Date(date.getTime());
		}
		if (date != null) {
			ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(getTimeZoneID(date)));
		}
		return ldt;
	}

	public static String getTimeZoneID(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeZone().getID();
	}
}
