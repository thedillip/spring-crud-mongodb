package com.application.api.component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.application.api.request.WeightSlipClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobSchedulerComponent {

	private final HttpClient httpClient;

	@Value("${render.base.url}")
	private String renderBaseUrl;

//	@Scheduled(cron = "0 0/5 * * * *")
	public void callRenderStartApi() throws IOException, InterruptedException {
		log.info("Running Scheduler -- JobSchedulerComponent::callRenderStartApi()");
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).GET().uri(URI.create(renderBaseUrl))
				.build();

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		log.info("########## Response = {} ##########", httpResponse.body());
	}

//	@Scheduled(cron = "0 0/15 * * * *")
	public void callWeightSlipApi() throws IOException, InterruptedException {
		log.info("Running Scheduler -- JobSchedulerComponent::callWeightSlipApi()");
		WeightSlipClientRequest weightSlipClientRequest = WeightSlipClientRequest.builder().address("BOUDH")
				.vehicleNumber("OD02AB8329").grossWeight("40000").tareWeight("20000")
				.grossWeightDate(LocalDateTime.now().toString()).tareWeightDate(LocalDateTime.now().toString()).build();

		String inputJson = new ObjectMapper().writeValueAsString(weightSlipClientRequest);

		HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(renderBaseUrl + "weightslip"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		log.info("########## Returned Response after hitting the weight slip API :: Status Code = {} ##########",
				httpResponse.statusCode());
	}
}
