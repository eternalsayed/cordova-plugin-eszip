# cordova-plugin-eszip
Cordova plugin to provide zip functionality on hybrid applications, such as on those made with Ionic framework. The Java code is mostly borrowed from StackOverflow and so, I've done preprocessing in Javascript. Change it as it may suit you.

# Why was this plugin needed?
The official cordova plugin for zipping failed for me, resulting in `bad zip`. I tried to debug it but couldn't so I thought why not make a simpler one which gaurantees solution.

# Getting started
Install the plugin by using following command:

<pre><code>
cordova plugin add cordova-plugin-eszip
</code></pre>
Thereafter, the global object `ESzip` will be available in your app. Please note that this will not run on browsers so please test on actual devices/simulators only.

# How to zip a folder?
* `ESzip` exposes function `zipFolder`, using which you can zip your source folder into destination zip file. Please note that `source` and `destination` are meant to be absolute paths, wherein `source` has to be an existing directory/file.
* `zipFolder` requires 4 parameters, namely: `source`, `destination`, `successCallback`, and `errorCallback`. `source` and `destination` have to be absolute file URLs.

# Example
<pre>
<code>
 angular.module('starter.services',[])
 .factory('$zip', function($q)
 {
   return {
    zip: function(src, dest)
    {
        var deferred = $q.defer();
        ESzip.zipFolder(src, dest, function(result)
        {
            if(result && result.success)//just to be sure
                deferred.resolve(result);
            else
                deferred.reject(result);
        },
        function(err){
            console.error(err);
            deferred.reject(err);
        })
        return deferred.promise;
    }
   };
 });
</code>
</pre>
