package com.ddamiani.codejam.mirror;

import com.ddamiani.codejam.mirror.MirrorRoom.MirrorType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests of the mirror room
 */
public final class MirrorRoomTest {
    private static final int NUM_COLUMNS = 4;
    private static final int NUM_ROWS = 5;
    private static final String[] ARGS = {"####", "#..#", "#.X#", "#..#", "####"};
    private MirrorRoom mirrorRoom;

    @Before
    public final void setUp() {
        mirrorRoom = new MirrorRoom(NUM_ROWS, NUM_COLUMNS, ARGS);
    }

    @Test
    public final void testPositions() {
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(0, 0));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(0, 1));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(0, 2));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(0, 3));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(1, 0));
        assertEquals(MirrorType.EMPTY, mirrorRoom.getTypeAt(1, 1));
        assertEquals(MirrorType.EMPTY, mirrorRoom.getTypeAt(1, 2));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(1, 3));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(2, 0));
        assertEquals(MirrorType.EMPTY, mirrorRoom.getTypeAt(2, 1));
        assertEquals(MirrorType.PERSON, mirrorRoom.getTypeAt(2, 2));
        assertEquals(MirrorType.MIRROR, mirrorRoom.getTypeAt(2, 3));
    }
}
