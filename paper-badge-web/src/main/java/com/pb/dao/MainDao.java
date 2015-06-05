package com.pb.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.activity.InvalidActivityException;

import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidSearchResult;

import com.pb.bean.MainBean;
import com.pb.util.HttpUtils;
import com.pb.util.MailUtils;
import com.pb.util.PropertyUtils;

public class MainDao {
	
	private static final String BASE_URI = "http://localhost:8080/paper-badge-web/pbMain/authenticate.do?code=";
	
	public static String createBadgeLinkAndSendEmail(MainBean mainBean) {
		String key = UUID.randomUUID().toString();
		String badgeLink = new StringBuffer(BASE_URI).append(key).toString();
		
		StringBuffer text = new StringBuffer("Greetings from PaperBadges./n/n")
		.append("Here is your badge that you requested. ").append("Please click the following link for validation.\n\n").append(badgeLink);
		MailUtils.sendMail(mainBean.getEmailAddr(), badgeLink, text.toString());
		if(mainBean.getContributors() != null && !"".equals(mainBean.getContributors().trim())) {
			text = new StringBuffer("Greetings from PaperBadges./n/n")
			.append("You have been issued a badge by your fellow researcher. ").append("Please click the following link for validation.\n\n").append(badgeLink);
			MailUtils.sendMail(mainBean.getContributors(), badgeLink, text.toString());
		}
		
		return key;
	}
	
	public void saveFormAsProperty(String key, MainBean mainBean) throws FileNotFoundException, IOException {
		StringBuffer value = new StringBuffer();
		value.append(mainBean.getDoi()).append("|").append(mainBean.getEmailAddr()).append("|")
			 .append((mainBean.getOrcid() == null || "".equals(mainBean.getOrcid() )) ? " " : mainBean.getOrcid()).append("|")
			 .append((mainBean.getContributors() == null || "".equals(mainBean.getContributors() )) ? " " : mainBean.getContributors());
		PropertyUtils.addProperty("badge.properties", key, value.toString());
	}

	public boolean validateAuthenticationCode(String code) throws Exception {
		String value = null;
		try {
			value = PropertyUtils.getProperty("badge.properties", code);
		} catch (FileNotFoundException e) {
			throw new InternalError("Problems with the application");
		} catch (IOException e) {
			throw new InternalError("Problems with the application");
		}
		
		if(value == null) {
			throw new InvalidActivityException("The link has been expired. Please try again.");
		} else {
			String[] arrProps = value.split("\\|");
			return validateDoi(arrProps[0], arrProps[1]);
		}
	}

	private boolean validateDoi(String doi, String email) throws Exception {
		String url = new StringBuffer("http://pub.sandbox.orcid.org/v1.2/search/orcid-bio/?q=digital-object-ids:(").append(doi).append(")").toString();
		OrcidMessage message = HttpUtils.runHttpGet(url);
		boolean result = false;
		if(message != null && message.getOrcidSearchResults() != null && message.getOrcidSearchResults().getOrcidSearchResult().size() > 0) {
			for(OrcidSearchResult searchResult : message.getOrcidSearchResults().getOrcidSearchResult()) {
				if(searchResult != null && searchResult.getOrcidProfile() != null 
						&& searchResult.getOrcidProfile().getOrcidBio() != null 
						&& searchResult.getOrcidProfile().getOrcidBio().getContactDetails() != null 
						&& searchResult.getOrcidProfile().getOrcidBio().getContactDetails().getEmail() != null 
						&& !searchResult.getOrcidProfile().getOrcidBio().getContactDetails().getEmail().isEmpty()
						&& searchResult.getOrcidProfile().getOrcidBio().getContactDetails().getEmailByString(email) != null) {
					result = true;
				}
			}
		}
		return result;
	}
}
