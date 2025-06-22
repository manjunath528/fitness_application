package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message_center")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCenter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_message_center")
    @SequenceGenerator(name = "id_sequence_message_center", sequenceName = "sequence_message_center", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "target_login_id")
    private String targetLoginId;

    @Column(name = "source_login_id")
    private String sourceLoginId;

    @Column(name = "message")
    private String message;

    @Column(name = "message_acceptance_status")
    private String messageAcceptanceStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "is_read")
    private String isRead;

    @Column(name = "created_ts")
    private Timestamp createdTs;

    @Column(name = "updated_ts")
    private Timestamp updatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetLoginId() {
        return targetLoginId;
    }

    public void setTargetLoginId(String targetLoginId) {
        this.targetLoginId = targetLoginId;
    }

    public String getSourceLoginId() {
        return sourceLoginId;
    }

    public void setSourceLoginId(String sourceLoginId) {
        this.sourceLoginId = sourceLoginId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageAcceptanceStatus() {
        return messageAcceptanceStatus;
    }

    public void setMessageAcceptanceStatus(String messageAcceptanceStatus) {
        this.messageAcceptanceStatus = messageAcceptanceStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public Timestamp getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Timestamp updatedTs) {
        this.updatedTs = updatedTs;
    }

    @Override
    public String toString() {
        return "MessageCenter{" +
                "id=" + id +
                ", targetLoginId='" + targetLoginId + '\'' +
                ", sourceLoginId='" + sourceLoginId + '\'' +
                ", message='" + message + '\'' +
                ", messageAcceptanceStatus='" + messageAcceptanceStatus + '\'' +
                ", status='" + status + '\'' +
                ", isRead='" + isRead + '\'' +
                ", createdTs='" + createdTs + '\'' +
                ", updatedTs='" + updatedTs + '\'' +
                '}';
    }
}