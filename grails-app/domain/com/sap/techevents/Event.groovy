package com.sap.techevents

class Event {

	String name
	String description
	Date eventDate
	Date dateCreated
	Date lastUpdated

    static constraints = {
    	name blank: false
    	description blank: true, widget: 'textarea'
    	eventDate blank: false
    	dateCreated display: false
    	lastUpdated display: false
    }
}
