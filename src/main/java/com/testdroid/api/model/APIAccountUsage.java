package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.SELF_URI;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonIgnoreProperties(value = {ID, SELF_URI})
public class APIAccountUsage extends APIEntity {

    private long timestamp;

    private String timestampLabel;

    private long sessionsCount;

    private long automatedUsage;

    private long automatedConcurrency;

    private long manualUsage;

    private long manualConcurrency;

    private long dedicatedUsage;

    private long dedicatedConcurrency;

    @XmlType(namespace = "APIAccountUsage")
    public enum Grouping {
        HOUR("yyyy-MM-dd HH:00:00", ChronoUnit.HOURS),
        DAY("yyyy-MM-dd", ChronoUnit.DAYS),
        WEEK("yyyy-MM-dd", ChronoUnit.WEEKS),
        MONTH("yyyy-MM", ChronoUnit.MONTHS);

        private final DateTimeFormatter dateFormatter;

        private final ChronoUnit chronoUnit;

        Grouping(String datePattern, ChronoUnit chronoUnit) {
            this.dateFormatter = DateTimeFormatter.ofPattern(datePattern);
            this.chronoUnit = chronoUnit;
        }

        public DateTimeFormatter getDateFormatter() {
            return dateFormatter;
        }

        public ChronoUnit getChronoUnit() {
            return chronoUnit;
        }
    }

    @XmlType(namespace = "APIAccountUsage")
    public enum SessionType {
        ALL,
        AUTOMATED,
        MANUAL
    }

    @XmlType(namespace = "APIAccountUsage")
    public enum UtilizationType {
        DEDICATED,
        ALL,
        PUBLIC
    }

    @XmlType(namespace = "APIAccountUsage")
    public enum Type {
        DEDICATED_AUTOMATED,
        DEDICATED_MANUAL,
        ALL,
        AUTOMATED,
        MANUAL
    }

    public APIAccountUsage() {
    }

    @SuppressWarnings("squid:S107")
    public APIAccountUsage(
            long timestamp, long sessionsCount, long automatedUsage, long manualUsage, long dedicatedUsage,
            long automatedConcurrency, long manualConcurrency, long dedicatedConcurrency) {
        this.timestamp = timestamp;
        this.sessionsCount = sessionsCount;
        this.automatedUsage = automatedUsage;
        this.manualUsage = manualUsage;
        this.dedicatedUsage = dedicatedUsage;
        this.automatedConcurrency = automatedConcurrency;
        this.manualConcurrency = manualConcurrency;
        this.dedicatedConcurrency = dedicatedConcurrency;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestampLabel() {
        return timestampLabel;
    }

    public void setTimestampLabel(String timestampLabel) {
        this.timestampLabel = timestampLabel;
    }

    public long getSessionsCount() {
        return sessionsCount;
    }

    public void setSessionsCount(long sessionsCount) {
        this.sessionsCount = sessionsCount;
    }

    public long getAutomatedUsage() {
        return automatedUsage;
    }

    public void setAutomatedUsage(long automatedUsage) {
        this.automatedUsage = automatedUsage;
    }

    public long getAutomatedConcurrency() {
        return automatedConcurrency;
    }

    public void setAutomatedConcurrency(long automatedConcurrency) {
        this.automatedConcurrency = automatedConcurrency;
    }

    public long getManualUsage() {
        return manualUsage;
    }

    public void setManualUsage(long manualUsage) {
        this.manualUsage = manualUsage;
    }

    public long getManualConcurrency() {
        return manualConcurrency;
    }

    public void setManualConcurrency(long manualConcurrency) {
        this.manualConcurrency = manualConcurrency;
    }

    public long getDedicatedUsage() {
        return dedicatedUsage;
    }

    public void setDedicatedUsage(long dedicatedUsage) {
        this.dedicatedUsage = dedicatedUsage;
    }

    public long getDedicatedConcurrency() {
        return dedicatedConcurrency;
    }

    public void setDedicatedConcurrency(long dedicatedConcurrency) {
        this.dedicatedConcurrency = dedicatedConcurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        APIAccountUsage that = (APIAccountUsage) o;

        return new EqualsBuilder().append(timestamp, that.timestamp)
                .append(sessionsCount, that.sessionsCount).append(automatedUsage, that.automatedUsage)
                .append(automatedConcurrency, that.automatedConcurrency).append(manualUsage, that.manualUsage)
                .append(manualConcurrency, that.manualConcurrency).append(dedicatedUsage, that.dedicatedUsage)
                .append(dedicatedConcurrency, that.dedicatedConcurrency).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(timestamp).append(sessionsCount).append(automatedUsage)
                .append(automatedConcurrency).append(manualUsage).append(manualConcurrency).append(dedicatedUsage)
                .append(dedicatedConcurrency).toHashCode();
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAccountUsage original = (APIAccountUsage) from;
        cloneBase(from);
        this.timestamp = original.timestamp;
        this.timestampLabel = original.timestampLabel;
        this.sessionsCount = original.sessionsCount;
        this.automatedUsage = original.automatedUsage;
        this.manualUsage = original.manualUsage;
        this.dedicatedUsage = original.dedicatedUsage;
        this.automatedConcurrency = original.automatedConcurrency;
        this.manualConcurrency = original.manualConcurrency;
        this.dedicatedConcurrency = original.dedicatedConcurrency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timestamp", timestamp)
                .append("timestampLabel", timestampLabel)
                .append("sessionsCount", sessionsCount)
                .append("automatedUsage", automatedUsage)
                .append("automatedConcurrency", automatedConcurrency)
                .append("manualUsage", manualUsage)
                .append("manualConcurrency", manualConcurrency)
                .append("dedicatedUsage", dedicatedUsage)
                .append("dedicatedConcurrency", dedicatedConcurrency)
                .toString();
    }
}
