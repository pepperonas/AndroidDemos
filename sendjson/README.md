# SendJson

This module shows how to send a JSON Object to a remote. The code is able to handle large overhead and reduces the overall size of the send-message.

Logging the code:

```
Mapping...
[{"size":10},{"some_variable_name_with_many_characters_1":"s1","some_variable_name_with_many_characters_2":"s2","some_variable_name_with_many_characters_3":"s3"},[{"s1":"A0","s2":"B0","s3":"0"},{"s1":"A1","s2":"B1","s3":"1"},{"s1":"A2","s2":"B2","s3":"2"},{"s1":"A3","s2":"B3","s3":"3"},{"s1":"A4","s2":"B4","s3":"4"},{"s1":"A5","s2":"B5","s3":"5"},{"s1":"A6","s2":"B6","s3":"6"},{"s1":"A7","s2":"B7","s3":"7"},{"s1":"A8","s2":"B8","s3":"8"},{"s1":"A9","s2":"B9","s3":"9"}]]

Insert...
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A0','B0','0' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A1','B1','1' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A2','B2','2' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A3','B3','3' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A4','B4','4' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A5','B5','5' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A6','B6','6' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A7','B7','7' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A8','B8','8' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'A9','B9','9' )
```


## Structure & Setup

The server-side code is located in `php_server`. Use XAMPP to run the code inside `server.php`.

Don't forget to alter the IP-address inside `MainActivity.java`.

You may want to use `ifconfig` (macOS / Linux) or `ipconfig` (Windows) to get your IP-address.


## Permissions

`<uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`


## Dependencies

Add in the module gradle `useLibrary 'org.apache.http.legacy'`


## Contact

* Martin Pfeffer - https://celox.io - <martin.pfeffer@celox.io>


## License

    Copyright 2017 Martin Pfeffer

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

