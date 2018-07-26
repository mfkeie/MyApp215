package com.elegion.myfirstapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.elegion.myfirstapplication.model.Album;
import com.elegion.myfirstapplication.model.AlbumSong;
import com.elegion.myfirstapplication.model.Comment;
import com.elegion.myfirstapplication.model.Song;

import java.util.List;

/**
 * @author Azret Magometov
 */

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbum(Album albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long setLinkAlbumSongs(AlbumSong linkAlbumSongs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComments(List<Comment> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(Comment comment);

    @Query("SELECT * from album")
    List<Album> getAlbums();

    @Query("SELECT * from comment  where album_id = :albumId")
    List<Comment> getComments(int albumId);

    //удалить альбом
    @Delete
    void deleteAlbum(Album album);

    //удалить альбом по id
    @Query("DELETE FROM album where id = :albumId")
    void deleteAlbumById(int albumId);

    @Query("select * from album where id = :albumId")
    Album getAlbumWithId(int albumId);

    //получить список песен переданного id альбома
    @Query("select * from song inner join albumsong on song.id = albumsong.song_id where album_id = :albumId")
    List<Song> getSongsFromAlbum(int albumId);
}
