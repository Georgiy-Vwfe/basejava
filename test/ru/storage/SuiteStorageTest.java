package ru.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        UuidMapStorageTest.class,
        ResumeMapStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class
})

public class SuiteStorageTest {
}
