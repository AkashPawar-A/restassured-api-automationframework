package com.onsite.payloadbuilder;

import com.onsite.pojo_request.CreditNoteCreateRequest;

public class CreditNotePayload {

	public static CreditNoteCreateRequest buildCreditNotePayload() {

		CreditNoteCreateRequest payload = new CreditNoteCreateRequest();

		payload.setProject_id("bfe6f3e8-fc36-4549-abdd-2c4623bd8899");
		payload.setInvoice_date("2025-07-22T10:56:44.395Z");
		payload.setAmount(50);
		payload.setNotes("check - 1");
		payload.setReference_number("A-001");
		payload.setParty_company_user_id("51669b2d-a55f-4507-9102-477eb52e100d");
		
		String[] photoArray = {"https://onsitedev.blr1.digitaloceanspaces.com/material/017ba90c-fd11-4f9f-9f95-80632b07d84a_c5b0bdca-6941-4411-aa64-369f6bb8cc32.png"};
		payload.setPhotos(photoArray);
		
		return payload;
	}
}
