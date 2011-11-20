WorldEditCUI
------------

The Worldedit Client User Interface


Current state
-------------

Master *should* be the stable state of the last release. I'm not sure of this, though, so it could probably use some checking. I have some untested work in the 'unchecked' branch, and I'm not entirely sure what changes I've made (I never released them, I think. not actually sure ...)

I build it with my simple-deobf tool. I do not wish to release this on my github at the moment, so if you wish to use this code exactly as-is, please contact me for a copy of the tool. Otherwise, the only significant difference from plain obfuscated modding is that everything that would normally be in the default package has been autorefactored to deobf/. I recommend using MCP, and if you have some time to fix MCP's dumb build system into a state usable by maven, also using maven would be a good idea. Poke me on irc for a rundown of generally what would be needed to mavenize MCP (I began doing it, but as mcp's code is weird I stopped.)


Future plans
------------

- Prediction of all worldedit commands so that you don't have to wait for the server to update - would require a local copy of worldedit, and would require that copy's version to match the server's version. To do this, protocol would have to be added to check if the worldedit versions match. If the version on the server does not match the local copy, a warning should be displayed to the user informing them that code may have changed and they may see incorrect local predictions.
- Preview of brush effects (Preferably, this would also integrate with voxelsniper) - The voxelsniper guys had some cool ideas as to how to do this, but the general gist of all ideas is to show some sort of transient display that indicates what will happen if you use a brush where you're looking
- Tool overlays - alter item rendering to display what items are associated with what tools (not difficult, would require the server to tell the client when a tool is bound to an item - note that tools are bound to item ids, not to individual instances of that item)


Again, I have some ideas as to how to implement much of this stuff, and if you'd like a description of what I was planning poke me on IRC.


irc://irc.esper.net/#lahwran
http://webchat.esper.net/?channels=lahwran
