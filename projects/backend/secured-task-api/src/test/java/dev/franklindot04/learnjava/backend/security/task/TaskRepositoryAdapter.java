package dev.franklindot04.learnjava.backend.security.task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

class TaskRepositoryAdapter implements TaskRepository {
    private final Map<Long, TaskEntity> tasks = new LinkedHashMap<>();
    private long nextId = 1;

    @Override
    public List<TaskEntity> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<TaskEntity> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public <S extends TaskEntity> S save(S entity) {
        Long id = entity.getId() == null ? nextId++ : entity.getId();
        TaskEntity saved = new TestTaskEntity(id, entity.getTitle(), entity.getDescription(), entity.isCompleted());
        tasks.put(id, saved);
        return (S) saved;
    }

    @Override
    public void delete(TaskEntity entity) {
        tasks.remove(entity.getId());
    }

    private static class TestTaskEntity extends TaskEntity {
        private final Long testId;

        TestTaskEntity(Long id, String title, String description, boolean completed) {
            super(title, description, completed);
            this.testId = id;
        }

        @Override
        public Long getId() {
            return testId;
        }
    }

    @Override public void flush() { throw unsupported(); }
    @Override public <S extends TaskEntity> S saveAndFlush(S entity) { return save(entity); }
    @Override public <S extends TaskEntity> List<S> saveAllAndFlush(Iterable<S> entities) { throw unsupported(); }
    @Override public void deleteAllInBatch(Iterable<TaskEntity> entities) { throw unsupported(); }
    @Override public void deleteAllByIdInBatch(Iterable<Long> longs) { throw unsupported(); }
    @Override public void deleteAllInBatch() { throw unsupported(); }
    @Override public TaskEntity getOne(Long aLong) { throw unsupported(); }
    @Override public TaskEntity getById(Long aLong) { throw unsupported(); }
    @Override public TaskEntity getReferenceById(Long aLong) { throw unsupported(); }
    @Override public <S extends TaskEntity> Optional<S> findOne(Example<S> example) { throw unsupported(); }
    @Override public <S extends TaskEntity> List<S> findAll(Example<S> example) { throw unsupported(); }
    @Override public <S extends TaskEntity> List<S> findAll(Example<S> example, Sort sort) { throw unsupported(); }
    @Override public <S extends TaskEntity> Page<S> findAll(Example<S> example, Pageable pageable) { throw unsupported(); }
    @Override public <S extends TaskEntity> long count(Example<S> example) { throw unsupported(); }
    @Override public <S extends TaskEntity> boolean exists(Example<S> example) { throw unsupported(); }
    @Override public <S extends TaskEntity, R> R findBy(Example<S> example, java.util.function.Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { throw unsupported(); }
    @Override public <S extends TaskEntity> List<S> saveAll(Iterable<S> entities) { throw unsupported(); }
    @Override public List<TaskEntity> findAllById(Iterable<Long> longs) { throw unsupported(); }
    @Override public boolean existsById(Long aLong) { return tasks.containsKey(aLong); }
    @Override public long count() { return tasks.size(); }
    @Override public void deleteById(Long aLong) { tasks.remove(aLong); }
    @Override public void deleteAllById(Iterable<? extends Long> longs) { throw unsupported(); }
    @Override public void deleteAll(Iterable<? extends TaskEntity> entities) { throw unsupported(); }
    @Override public void deleteAll() { tasks.clear(); }
    @Override public List<TaskEntity> findAll(Sort sort) { return findAll(); }
    @Override public Page<TaskEntity> findAll(Pageable pageable) { throw unsupported(); }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException("Not needed for this focused service test");
    }
}
