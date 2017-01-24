var exec = require('cordova/exec');

function ESzip() { }

ESzip.prototype.zipFolder = function(source, destination, success, error) {
    var src = source.split(':/')[1];
    src = '/'+src.replace(/^\/?|\/?$/, "");
    var dest = destination.split(':/')[1];
    dest = '/'+dest.replace(/^\/?|\/?$/, "");
    //console.log('Source: ',src,'\nOriginal source:',source,' \nDest:', dest,'\nOriginal dest:',destination);
   
    exec(success, error, "ESzip", "zipFolder", [src, dest]);
};
module.exports = new ESzip();