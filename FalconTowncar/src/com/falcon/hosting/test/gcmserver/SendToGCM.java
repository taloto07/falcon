package com.falcon.hosting.test.gcmserver;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class SendToGCM {
	public static void main(String[] args) throws IOException{
		String projectId = "AIzaSyAb9uvsLAhq80R-gYBBStg8H-xTqopvxwk";
		String regId = "APA91bE-CYsn2HI70i2m3BZWdSRYbeVHmUTGEEXcnLz0bFJ9wS-gade96vwh5dAgKWheX52FoAevhSB-70ZI-66jpEA9MsiKT-ufuAPCJiK8TDFyN720NvkKuhvAZFAwfUWxV_cxmi_LPHmQxmlptMoM5aG6O19nQw";
		
		Sender sender = new Sender(projectId);
		Message message = new Message.Builder().timeToLive(30)
				.delayWhileIdle(true).addData("message","hello world").build();
//		System.out.println("regId: " + "APA91bE-CYsn2HI70i2m3BZWdSRYbeVHmUTGEEXcnLz0bFJ9wS-gade96vwh5dAgKWheX52FoAevhSB-70ZI-66jpEA9MsiKT-ufuAPCJiK8TDFyN720NvkKuhvAZFAwfUWxV_cxmi_LPHmQxmlptMoM5aG6O19nQw");
		Result result = sender.send(message, regId, 1);
	}
}
