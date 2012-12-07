package co.uk.geekonabicycle.icalextractor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {

	private static final String dateFormatString = "yyyyMMdd'T'HHmmS'Z'";

	private String dtStart = "";
	private String dtEnd = "";
	private String dtStamp = "";
	private String uid;
	private String created;
	private String description;
	private String lastModified;
	private String location;
	private String sequence;
	private String status;
	private String summary;
	private String transp;

	private SimpleDateFormat dateFormatter;
	
	public Entry ()
	{
		dateFormatter = new SimpleDateFormat(dateFormatString);
	}

	public String getTransp() {
		return transp;
	}

	public void setTransp(String transp) {
		this.transp = transp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getDtEnd() throws ParseException {
		return dateFormatter.parse(dtEnd);
	}

	public void setDtEnd(String dtEnd) {
		this.dtEnd = dtEnd;
	}

	public Date getDtStart() throws ParseException {
		return dateFormatter.parse(dtStart);
	}

	public void setDtStart(String dtStart) {
		this.dtStart = dtStart;
	}

	public Date getDtStamp() throws ParseException {
		return dateFormatter.parse(dtStamp);
	}

	public void setDtStamp(String dtStamp) {
		this.dtStamp = dtStamp;
	}

}
