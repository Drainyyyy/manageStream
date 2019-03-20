# Contributing
If you want to contribute to this project please read this.
## General
You are welcome to contribute to this project, but make sure to read the following points to make sure there won't be any complications.
## Development
### Documentation
If you want to develop this project please document your code well. Use the same schema as I used for documentation.
```java
/** Short description
 * <br>
 * Long description
 * 
 * @param x If params given describe them here.
 * @param y If params given describe them here.
 * @return Type and description of the returned object.
 * 
 * @see If needed mention objects here
 * 
 * @since current version
 */
public HashMap<String, String> example(String x, String y) {
	return new HashMap<>(){{
	put("x", x);
	put("y", y);
	}};
}
```
Also make sure you quote yourself as author in the respective class. e.g.:
```java
package com.github.drainyyy.manageStream.core;

/**
 * @author yourGitHubName
 * https://github.com/yourGitHubName
 */
public class ExampleClass {
	public static void exit() {
		System.out.println("exit");
	}
}
```
### Code
The goal of every project (at least for me) is human readability. 
So please try not to write a mess and if you find a mess in my code (and sometimes my code is a really big mess) you can message me (or you can clean it up for me lol).
## Issues & Bugs
If you found a bug please report it to the issues page for this project and label it with the bug label.
The issue page can be found [here](https://github.com/drainyyyy/manageStream/issues).

If you found an issue that you want to fix please comment below it that you'll try to fix it.
When you fixed it just create a pull request.
