package org.zalando.nakadi.repository.kafka;


import com.google.common.base.Charsets;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;

/**
 * Class represents serializer / deserializer of event authentication header.
 */
public class EventAuthHeader extends RecordHeader {

    static final String HEADER_KEY = "X-Event-Auth";

    public EventAuthHeader(final String value) {
        super(HEADER_KEY, value == null ? null : value.getBytes(Charsets.UTF_8));
    }

    public static EventAuthHeader extractFrom(ConsumerRecord<byte[], byte[]> record) {
        final Header header = record.headers().lastHeader(EventAuthHeader.HEADER_KEY);
        if (header == null) {
            return new EventAuthHeader(null);
        }
        return (EventAuthHeader) header;
    }

    public String getEventAuthValue() {
        return new String(value(), Charsets.UTF_8);
    }

    public static EventAuthHeader valueOf(final Header header) {
        if (HEADER_KEY.equals(header.key())) {
            return (EventAuthHeader) header;
        }

        throw new IllegalArgumentException(String.format("Can not parse header with key: %s", header.key()));
    }
}
