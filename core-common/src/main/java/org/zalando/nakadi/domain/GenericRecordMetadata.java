package org.zalando.nakadi.domain;

import org.apache.avro.generic.GenericRecord;

import java.util.Arrays;
import java.util.List;

public class GenericRecordMetadata implements NakadiMetadata {

    public static final String EID = "eid";
    public static final String OCCURRED_AT = "occurred_at";
    public static final String PUBLISHED_BY = "published_by";
    public static final String RECEIVED_AT = "received_at";
    public static final String EVENT_TYPE = "event_type";
    public static final String FLOW_ID = "flow_id";
    public static final String SCHEMA_VERSION = "version";
    public static final String PARTITION = "partition";
    public static final String PARTITION_KEYS = "partition_keys";
    public static final String PARTITION_COMPACTION_KEY = "partition_compaction_key";

    private final GenericRecord metadata;
    private final byte metadataVersion;

    public GenericRecordMetadata(final GenericRecord metadata,
                                 final byte metadataVersion) {
        this.metadata = metadata;
        this.metadataVersion = metadataVersion;
    }

    public GenericRecord getMetadata() {
        return this.metadata;
    }

    public byte getMetadataVersion() {
        return this.metadataVersion;
    }

    public String getEid() {
        return this.metadata.get(EID).toString();
    }

    public String getEventType() {
        return this.metadata.get(EVENT_TYPE).toString();
    }

    @Override
    public void setEventType(final String eventType) {
        this.metadata.put(EVENT_TYPE, eventType);
    }

    public String getPartitionStr() {
        final Object partition = this.metadata.get(PARTITION);
        if (partition == null) {
            return null;
        }
        return partition.toString();
    }

    public Integer getPartitionInt() {
        final Object partition = this.metadata.get(PARTITION);
        if (partition == null) {
            return null;
        }
        return Integer.valueOf(partition.toString());
    }

    @Override
    public void setPartition(final String partition) {
        this.metadata.put(PARTITION, partition);
    }

    @Override
    public long getOccurredAt() {
        return (Long) this.metadata.get(OCCURRED_AT);
    }


    @Override
    public String getPublishedBy() {
        return this.metadata.get(PUBLISHED_BY).toString();
    }

    @Override
    public void setPublishedBy(final String publisher) {
        this.metadata.put(PUBLISHED_BY, publisher);
    }

    @Override
    public long getReceivedAt() {
        return (Long) this.metadata.get(RECEIVED_AT);
    }

    @Override
    public void setReceivedAt(final long receivedAt) {
        this.metadata.put(RECEIVED_AT, receivedAt);
    }

    @Override
    public String getFlowId() {
        return this.metadata.get(FLOW_ID).toString();
    }

    @Override
    public void setFlowId(final String flowId) {
        this.metadata.put(FLOW_ID, flowId);
    }

    @Override
    public String getSchemaVersion() {
        return this.metadata.get(SCHEMA_VERSION).toString();
    }

    @Override
    public void setSchemaVersion(final String schemaVersion) {
        this.metadata.put(SCHEMA_VERSION, schemaVersion);
    }

    @Override
    public List<String> getPartitionKeys() {
        final Object partitionKeys = this.metadata.get(PARTITION_KEYS);
        if (partitionKeys == null) {
            return null;
        }

        return Arrays.asList((String[]) partitionKeys);
    }

    @Override
    public void setPartitionKeys(final List<String> partitionKeys) {
        this.metadata.put(PARTITION_KEYS, partitionKeys.toArray(new String[0]));
    }

    @Override
    public String getPartitionCompactionKey() {
        return this.metadata.get(PARTITION_COMPACTION_KEY).toString();
    }

    @Override
    public void setPartitionCompactionKey(final String partitionCompactionKey) {
        this.metadata.put(PARTITION_COMPACTION_KEY, partitionCompactionKey);
    }
}
