# SendJson

This module shows how to send a JSON Object to a remote. The code is able to handle large overhead and reduces the overall size of the message to be sent.

Logging the code:

```
Mapping...
[{"size":10},{"some_variable_name_with_many_characters_1":"s1","some_variable_name_with_many_characters_2":"s2","some_variable_name_with_many_characters_3":"s3"},[{"s1":"Alphaaa0","s2":"Betaaaa0","s3":"0"},{"s1":"Alphaaa1","s2":"Betaaaa1","s3":"1"},{"s1":"Alphaaa2","s2":"Betaaaa2","s3":"2"},{"s1":"Alphaaa3","s2":"Betaaaa3","s3":"3"},{"s1":"Alphaaa4","s2":"Betaaaa4","s3":"4"},{"s1":"Alphaaa5","s2":"Betaaaa5","s3":"5"},{"s1":"Alphaaa6","s2":"Betaaaa6","s3":"6"},{"s1":"Alphaaa7","s2":"Betaaaa7","s3":"7"},{"s1":"Alphaaa8","s2":"Betaaaa8","s3":"8"},{"s1":"Alphaaa9","s2":"Betaaaa9","s3":"9"}]]

Insert...
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa0','Betaaaa0','0' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa1','Betaaaa1','1' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa2','Betaaaa2','2' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa3','Betaaaa3','3' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa4','Betaaaa4','4' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa5','Betaaaa5','5' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa6','Betaaaa6','6' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa7','Betaaaa7','7' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa8','Betaaaa8','8' )
INSERT INTO data_table ( some_variable_name_with_many_characters_1,some_variable_name_with_many_characters_2,some_variable_name_with_many_characters_3 ) VALUES (  'Alphaaa9','Betaaaa9','9' )
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

