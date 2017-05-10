# SendJson

This module shows how to send a JSON Object to a servlet. The JSON Object will be written to a file called out.txt


## Structure & Setup

The server-side code is located in `php_server`. Use XAMPP to run the code inside `test.php`.

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

