# android-bookmarkSample

This project is not showing how to handle bookmark storing mechanism, 
instead it show you how to handle your activities so your item bookmark status 
is consistent across all pages like below:

<img src="https://github.com/pramonow/android-bookmarkSample/blob/master/screenshot.gif" width="180"> 

Flow on image:
1. Bookmark Object 1 and 2 (position 2nd and 3rd)
2. Navigate to Object 1 Activity
3. Unbookmark Object 1 from inside
4. Return to previous Activity
5. Object 1 is unbookmarked now, while Object 2 remains bookmarked

Basically it is using Broadcast Manager to achieve that :)
