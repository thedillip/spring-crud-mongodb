package com.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeightSlipClientRequest {
	private String address;
	private String vehicleNumber;
	private String grossWeight;
	private String tareWeight;
	private String grossWeightDate;
	private String tareWeightDate;
}
