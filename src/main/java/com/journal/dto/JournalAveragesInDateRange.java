package com.journal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JournalAveragesInDateRange {
    private int mentalToughness;
    private int willingness;
    private int determination;
    private int motivation;
    private int attitude;
    private int personalImpact;
}
