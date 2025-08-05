package com.onsite.endpoints;

public class CreditNote_Api {
	
	public static final String Create_CreaditNote = "/add/creditnote";
	public static final String Get_CreditNote = "/detail/creditnote/{id}";
	public static final String Edit_CreditNote = "/edit/creditnote";
	public static final String Delete_CreditNote = "delete/creditnote/{id}";
	public static final String List_CreditNote = "/list/creditnote";
	public static final String ListPartyP_CreaditNote = "/list/party/creditnote/projectlevel";
	public static final String ListPartyC_CreaditNote = "/list/party/creditnote/companylevel";
	public static final String Approval_CreditNote = "/creditnote/approval";
	public static final String CommentApproval_CreditNote = "/update/creditnote/approval/comment";
	public static final String AddItem_CreditNote = "/bulk/add/creditnoteitem";
	public static final String EditItem_CreditNote = "/bulk/edit/creditnoteitem";
	public static final String ListCreditNoteItem = "/list/creditnoteitem";
	
}
