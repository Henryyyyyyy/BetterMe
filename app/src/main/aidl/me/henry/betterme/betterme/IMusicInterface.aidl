// IMusicInterface.aidl
package me.henry.betterme.betterme;

// Declare any non-default types here with import statements
import me.henry.betterme.betterme.model.MusicInfo;
interface IMusicInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void playOrPause();
    void next();
    void previous();
    void getCurrentPosition();
    void playMusic(in MusicInfo music,int index);
    void setPlayMode(in int mode);
    MusicInfo getCurrentMusicInfo();
}
