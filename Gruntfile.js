module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        // Task: copy all files from  'raw-static-resources' to the 'static-resources' exclude *.coffee and *.scss files
        copy: {
            main: {
                src: ['**/*' , '!**/*.coffee', '!**/*.scss', '!**/readme'],  // copy masks
                expand: true,
                cwd: 'raw-static-resources',                                 // source directory
                dest: 'static-resources'                                     // target directory
            }
        },
        // Task: compile all coffee files and put them to the target directory
        coffee: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'raw-static-resources',                             // source directory
                    src: ['**/*.coffee'],                                    // file mask
                    dest: 'static-resources',                                // target directory
                    ext: '.js'                                               // final extension
                }]
            }
        },
        sass: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'raw-static-resources',                             // source directory
                    src: ['**/*.scss'],                                      // file mask
                    dest: 'static-resources',                                // target directory
                    ext: '.css'                                              // final extension
                }]
            }
        },
        // Task: watch for adding, changing and deleting files
        watch: {
            coffee: {
                files: 'raw-static-resources/**/*.coffee',                   // target directory
                tasks: ['coffee']                                            // target task
            },
            css: {
                files: 'raw-static-resources/**/*.scss',                     // target directory
                tasks: ['sass']                                              // target task
            },
            other: {
                files: ['raw-static-resources/**', 'raw-static-resources/**/!*.(scss)', 'raw-static-resources/**/!(*.coffee)'],
                tasks: ['clean', 'copy', 'coffee', 'sass']
            }
        },
        clean: ["static-resources"]
    });

//    grunt.event.on('watch', function(action, filepath, target) {
//        if (action === 'deleted') {
//                    grunt.log.write('action: ' + action + '   ');
//                    grunt.log.write('filepath: ' + filepath + '   ');
//                    grunt.log.write('target: ' + target + '   ');
//            extention = filepath.substr(filepath.lastIndexOf("."), filepath.size);
//                    grunt.log.write('extention: ' + extention + '   ');
//            fileToDelete = filepath.substr(4, filepath.lastIndexOf(".") - 4);
//            if (extention === '.scss') {
//                fileToDelete = fileToDelete + '.css';
//            }
//            if (extention === '.coffee') {
//                fileToDelete = fileToDelete + '.js';
//            }
//                    grunt.log.write('fileToDelete: ' + fileToDelete + '   ');
//
//            grunt.file.delete(fileToDelete);
//        }
//    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-coffee');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-clean');

    grunt.registerTask('default', ['clean', 'copy', 'coffee', 'sass', 'watch'])
};