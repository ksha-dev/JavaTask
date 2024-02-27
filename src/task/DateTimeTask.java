package task;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import exceptions.CustomException;
import utility.Utility;

public class DateTimeTask {
	public ZonedDateTime getLocalDateTime() {
		return ZonedDateTime.now();
	}

	public long getDateTimeInMillis() {
		return System.currentTimeMillis();
	}

	public ZonedDateTime getZoneDateTime(String zoneID) throws CustomException {
		Utility.validateObject(zoneID);
		try {
			return ZonedDateTime.now(ZoneId.of(zoneID));
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), e);
		}
	}

	public DayOfWeek getWeekDay(ZonedDateTime dateTime) throws CustomException {
		Utility.validateObject(dateTime);
		return dateTime.getDayOfWeek();
	}

	public Month getMonth(ZonedDateTime dateTime) throws CustomException {
		Utility.validateObject(dateTime);
		return dateTime.getMonth();
	}

	public int getYear(ZonedDateTime dateTime) throws CustomException {
		Utility.validateObject(dateTime);
		return dateTime.getYear();
	}
	
	public Period getDateDifference(ZonedDateTime dateTime1, ZonedDateTime dateTime2) throws CustomException {
		Utility.validateObject(dateTime1);
		Utility.validateObject(dateTime2);
		return Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
	}
	
	public Duration getTimeDifference(ZonedDateTime dateTime1, ZonedDateTime dateTime2) throws CustomException {
		Utility.validateObject(dateTime1);
		Utility.validateObject(dateTime2);
		return Duration.between(dateTime1.toLocalDateTime(), dateTime2.toLocalDateTime());
	}
	
	public Duration getOffsetDifference(ZonedDateTime dateTime1, ZonedDateTime dateTime2) throws CustomException {
		Utility.validateObject(dateTime1);
		Utility.validateObject(dateTime2);
		return Duration.ofHours(dateTime1.getOffset().compareTo(dateTime2.getOffset()));
	}

}
