package dto;

import model.LottoResult;

import java.util.Map;

public record LottoResultDto (Map<LottoResult, Integer> lottoResults) {}
