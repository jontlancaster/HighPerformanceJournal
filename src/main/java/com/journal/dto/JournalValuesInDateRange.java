package com.journal.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Builder
@Data
public class JournalValuesInDateRange {
    private Set<String> dates;
    private Map<String, Map<String, Integer>> dateValuesMap;
}
