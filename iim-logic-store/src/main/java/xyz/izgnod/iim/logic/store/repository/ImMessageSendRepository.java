package xyz.izgnod.iim.logic.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.izgnod.iim.logic.store.entity.ImMessageSend;

@Repository
public interface ImMessageSendRepository extends JpaRepository<ImMessageSend, Long> {
}