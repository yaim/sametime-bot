CREATE TABLE IF NOT EXISTS events (
    id          BIGINT NOT NULL PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    author_id   BIGINT NOT NULL,
    message_id  BIGINT NOT NULL,
    channel_id  BIGINT NOT NULL,
    server_id   BIGINT NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL,
    updated_at  TIMESTAMPTZ NOT NULL,
    deleted_at  TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS event_message_id ON events (message_id);
CREATE INDEX IF NOT EXISTS event_channel_id ON events (channel_id);

CREATE UNIQUE INDEX IF NOT EXISTS event_name_id ON events(channel_id, name);

