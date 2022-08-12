USE im;
DROP TABLE IF EXISTS im_message_receive;
DROP TABLE IF EXISTS im_message_send;

CREATE TABLE im_message_send
(
    msg_id       BIGINT           NOT NULL PRIMARY KEY,
    msg_type     TINYINT UNSIGNED NOT NULL,
    msg_from     VARCHAR(64)      NOT NULL,
    msg_to       VARCHAR(64)      NOT NULL,
    chat_id      VARCHAR(128)     NOT NULL,
    chat_type    TINYINT UNSIGNED NOT NULL,
    msg_seq      BIGINT UNSIGNED  NOT NULL,
    msg_content  VARCHAR(1024)    NOT NULL,
    content_type TINYINT UNSIGNED NOT NULL,
    send_time    TIMESTAMP        NOT NUll,
    delivered    INT UNSIGNED     NOT NULL,
    cmd_id       INT UNSIGNED     NOT NULl,
    server_seq   BIGINT UNSIGNED  NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

#
# CREATE TABLE im_message_send (
#
# )
#     im_message_send (msg_id,msg_from,msg_to, group_id，msg_seq, msg_content, send_time, msg_type, cmd_id)
--
-- #
-- #     send.setChatId(ChatIdUtil.getChatId(msgBean.getMsgFrom(), msgBean.getMsgTo()));
-- # s   end.setChatType(ChatTypeEnum.C2C_CHAT.getCode());
-- #      send.setMsgFrom(msgBean.getMsgFrom());
-- #         send.setFromDomain(msgBean.getFromDomain());
-- #         send.setMsgTo(msgBean.getMsgTo());
-- #         send.setToDomain(msgBean.getToDomain());
-- #         send.setContent(msgBean.getMsgContent());
-- #         send.setContentType(msgBean.getContentType());
-- #         send.setMsgId(msgBean.getSendMsgId());
-- #         send.setMsgSeq(msgBean.getSeq());
-- #         send.setMsgType(msgBean.getMsgType());
-- #         send.setSendTime(msgBean.getSendTime());
-- #         send.setCmdId(msgBean.getCmdId());
-- #         send.setServerSeq(msgBean.getServerSeq());