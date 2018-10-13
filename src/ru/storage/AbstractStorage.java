package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void saveEntity(Object identifier, Resume resume);

    protected abstract Object getIdentifier(String uuid);

    protected abstract void deleteEntity(Object identifier, String uuid);

    protected abstract void doUpdate(Object identifier, Resume resume);

    protected abstract Resume doGet(Object identifier, String uuid);

    public abstract void clear();


    @Override
    public void save(Resume resume) {
        Object identifier = getIdentifier(resume.getUuid());
        checkForExist(resume.getUuid(), identifier);
        checkForStorageLimit(resume.getUuid());
        saveEntity(identifier, resume);
    }

    @Override
    public void delete(String uuid) {
        Object identifier = getIdentifier(uuid);
        checkForNotExist(uuid, identifier);
        deleteEntity(identifier, uuid);
    }

    @Override
    public void update(Resume resume) {
        Object identifier = getIdentifier(resume.getUuid());
        checkForNotExist(resume.getUuid(), identifier);
        doUpdate(identifier, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object identifier = getIdentifier(uuid);
        checkForNotExist(uuid, identifier);
        return doGet(identifier, uuid);
    }

    protected void checkForStorageLimit(String uuid) {

    }

    private void checkForExist(String uuid, Object identifier) {
        try {
            if ((Integer) identifier >= 0) {
                throw new ExistStorageException(uuid);
            }
        } catch (Exception e) {
            if (identifier.equals(uuid)) {
                throw new ExistStorageException(uuid);
            }
        }
    }

    private void checkForNotExist(String uuid, Object identifier) {
        try {
            if ((Integer) identifier < 0) {
                throw new NotExistStorageException(uuid);
            }
        } catch (Exception e) {
            if (!identifier.equals(uuid)) {
                throw new ExistStorageException(uuid);
            }
        }
    }
}
