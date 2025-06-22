package com.gym.app.repository;

import com.gym.app.entity.MessageCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MessageCenterRepository extends JpaRepository<MessageCenter, Long> {
    @Query("SELECT messageCenter from MessageCenter messageCenter WHERE lower(messageCenter.targetLoginId) =lower(:targetLoginId) " +
            "ORDER BY messageCenter.createdTs DESC")
    List<MessageCenter> retrieveMessagesByLoginId(@Param("targetLoginId") String targetLoginId);

    @Query("SELECT messageCenter from MessageCenter messageCenter WHERE lower(messageCenter.targetLoginId) =lower(:targetLoginId) " +
            "AND lower(messageCenter.status) IN :statusList ORDER BY messageCenter.createdTs DESC")
    List<MessageCenter> retrieveMessagesByLoginIdAndStatus(@Param("targetLoginId") String targetLoginId, @Param("statusList") List<String> statusList);

    @Query("SELECT messageCenter from MessageCenter messageCenter WHERE lower(messageCenter.targetLoginId) =lower(:targetLoginId) " +
            "AND lower(messageCenter.status) IN :statusList AND lower(messageCenter.isRead) =:onlyReadMessages ORDER BY messageCenter.createdTs DESC")
    List<MessageCenter> retrieveMessagesByLoginIdAndMetaData(@Param("targetLoginId") String targetLoginId, @Param("statusList") List<String> statusList
            , @Param("onlyReadMessages") String onlyReadMessages);

    @Query("SELECT messageCenter from MessageCenter messageCenter WHERE messageCenter.createdTs BETWEEN :start AND :end " +
            "ORDER BY messageCenter.createdTs DESC")
    List<MessageCenter> retrieveLastXMonthsHistoricalMessageCenterData(@Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query("SELECT messageCenter FROM MessageCenter messageCenter WHERE messageCenter.updatedTs >= :yDate AND messageCenter.updatedTs < :tDate")
    List<MessageCenter> messagesByDate(@Param("yDate") Timestamp yDate, @Param("tDate") Timestamp tDate);
}
