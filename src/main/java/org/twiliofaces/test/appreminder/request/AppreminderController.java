package org.twiliofaces.test.appreminder.request;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.configuration.TwilioNumber;
import org.twiliofaces.annotations.configuration.TwilioSid;
import org.twiliofaces.annotations.configuration.TwilioToken;
import org.twiliofaces.request.TwilioCaller;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Call;

@Named
@RequestScoped
public class AppreminderController {

	@Inject
	TwilioCaller twilioCaller;

	private String number = "";
	// your Twilio authentication credentials
	@Inject
	@TwilioNumber
	String twilioNumber;

	@Inject
	@TwilioSid
	String twilioSid;

	@Inject
	@TwilioToken
	String twilioToken;

	public String makecall() {
		// Use the Twilio REST API to initiate an outgoing call
		if (getNumber() == null || getNumber().isEmpty()) {
			FacesMessage fm = new FacesMessage("Invalid phone number");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", fm);
			return null;
		} else {
			Map<String, String> callParams = new HashMap<String, String>();
			callParams.put("To", getNumber());
			callParams.put("From", twilioNumber);// twilioNumber
			callParams
					.put("Url",
							"https://duilio-j2flower.rhcloud.com/twiliofaces-app-reminder-0.0.1/reminder.jsf");

			try {
				Call result = twilioCaller.call(twilioSid, twilioToken,
						callParams);
				FacesMessage fm = new FacesMessage(
						"Ok! Twilio will call the number [" + getNumber()
								+ "]!");
				fm.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("", fm);
				return null;
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FacesMessage fm = new FacesMessage("Error in calling!");
				fm.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("", fm);
				return null;
			}
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
