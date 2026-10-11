package com.ghatwatch.risk;

import com.ghatwatch.domain.Reading;
import com.ghatwatch.report.ReportCatalog;

import java.math.BigDecimal;

/** A pointer from a claim back to the exact cell in the MPPCB report. */
public record ReadingRef(Long readingId, int year, String festival, String phase, String parameter,
                         BigDecimal value, String valueAsPrinted, String unit, int sourcePage,
                         String pdfUrl) {

    public static ReadingRef of(Reading r) {
        return new ReadingRef(r.getId(), r.getYear(), r.getFestival(), r.getPhase(), r.getParameter(),
                r.getValue(), r.getValueAsPrinted(), r.getUnitAssumed(), r.getSourcePage(),
                ReportCatalog.pdfUrl(r.getYear(), r.getSourcePage()));
    }

    /** "Ganesh 2025, during immersion, report p.28". */
    public String where() {
        return festival + " " + year + ", " + Labels.phase(phase) + ", " + year + " report p." + sourcePage;
    }

    public String whereHi() {
        return new EventKey(year, festival).labelHi() + ", " + Labels.phaseHi(phase)
                + ", " + year + " रिपोर्ट पृ." + sourcePage;
    }
}
