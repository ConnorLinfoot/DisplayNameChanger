DisplayNameChanger
==================
This plugin allows you to easily change the display name (Name Tag) above your head of any player including yourself. These only work for version 2.0+

For versions below 2.0 check the Bukkit page here: http://dev.bukkit.org/bukkit-plugins/dnc/

###Commands:

**/cdn** - Usage: /cdn \<name\> (Will change your Display Name) OR /cdn \<player\> \<name\> (Will change another players display name)  OR /cdn clear (Will clear the list of display names)

###Permissions:

**displayname.change.\*** - Default: OP, Allows you to use the command and all children of it

**displayname.change.self** - Default: OP, Allows you to change your display name

**displayname.change.others** - Default: OP, Allows you to change other players display name

**displayname.change.ignore** - Default: False, Allows you to protect yourself having your display name changed


###Config:

**Change Chat Display Name** - Default: false, If enabled will change your player display name in chat also, as long as no other plugin is overwriting it

**Remember Names** - Default: false, If enabled will remember names even after server reloads or reboots


###TagAPI

This plugin requires the TagAPI which can be found here: http://dev.bukkit.org/bukkit-plugins/tag/
