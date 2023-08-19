package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.QuestionGroupKey;
import by.ghoncharko.selectioninterview.entity.QuestionToGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionToGroupRepository extends JpaRepository<QuestionToGroup, QuestionGroupKey> {
    
}
