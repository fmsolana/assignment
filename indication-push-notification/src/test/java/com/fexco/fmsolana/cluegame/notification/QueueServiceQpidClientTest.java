package com.fexco.fmsolana.cluegame.notification;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.junit.Ignore;
import org.junit.Test;

public class QueueServiceQpidClientTest {

	private QueueServiceQpidClient service = new QueueServiceQpidClient();

	@Ignore
	@Test
	public void sendMessages() throws IOException, TimeoutException, NoSuchAlgorithmException {
		service.sendMessage("message1", 10);
	}

}
