package com.ghatwatch.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * One cell of an MPPCB visarjan table: a ghat, a festival, a phase and a parameter.
 * {@code value} is null when the report printed no number; {@code flag} then says why.
 */
@Entity
@Table(name = "reading")
public class Reading {

    public static final String FLAG_BDL = "BDL";
    public static final String FLAG_ND = "ND";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ghat_id")
    private String ghatId;

    @Column(name = "report_year")
    private int year;

    private String festival;

    /** pre, during or after (one week after) immersion. */
    private String phase;

    private String parameter;

    private BigDecimal value;

    @Column(name = "unit_assumed")
    private String unitAssumed;

    private String flag;

    @Column(name = "value_as_printed")
    private String valueAsPrinted;

    @Column(name = "parameter_as_printed")
    private String parameterAsPrinted;

    @Column(name = "site_as_printed")
    private String siteAsPrinted;

    @Column(name = "site_match_confidence")
    private String siteMatchConfidence;

    @Column(name = "source_page")
    private int sourcePage;

    protected Reading() {
    }

    public Reading(String ghatId, int year, String festival, String phase, String parameter,
                   BigDecimal value, String unitAssumed, String flag, String valueAsPrinted,
                   String parameterAsPrinted, String siteAsPrinted, String siteMatchConfidence,
                   int sourcePage) {
        this.ghatId = ghatId;
        this.year = year;
        this.festival = festival;
        this.phase = phase;
        this.parameter = parameter;
        this.value = value;
        this.unitAssumed = unitAssumed;
        this.flag = flag;
        this.valueAsPrinted = valueAsPrinted;
        this.parameterAsPrinted = parameterAsPrinted;
        this.siteAsPrinted = siteAsPrinted;
        this.siteMatchConfidence = siteMatchConfidence;
        this.sourcePage = sourcePage;
    }

    /** BDL / ND: tested, and very low. Not zero and not missing. */
    public boolean isBelowDetection() {
        return FLAG_BDL.equalsIgnoreCase(flag) || FLAG_ND.equalsIgnoreCase(flag);
    }

    /** No number and not BDL/ND: the cell was blank, "_", "-", "*" or "**". Never clean. */
    public boolean isMissing() {
        return value == null && !isBelowDetection();
    }

    public Long getId() { return id; }
    public String getGhatId() { return ghatId; }
    public int getYear() { return year; }
    public String getFestival() { return festival; }
    public String getPhase() { return phase; }
    public String getParameter() { return parameter; }
    public BigDecimal getValue() { return value; }
    public String getUnitAssumed() { return unitAssumed; }
    public String getFlag() { return flag; }
    public String getValueAsPrinted() { return valueAsPrinted; }
    public String getParameterAsPrinted() { return parameterAsPrinted; }
    public String getSiteAsPrinted() { return siteAsPrinted; }
    public String getSiteMatchConfidence() { return siteMatchConfidence; }
    public int getSourcePage() { return sourcePage; }
}
