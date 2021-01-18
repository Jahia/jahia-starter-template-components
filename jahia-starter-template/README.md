# Jahia Starter Template

## Usage

1. Create Jahia site and select `Start with your HTML` as template set
2. Using `jcontent` upload custom assets under `files/assets` according to desired functionality (refer to readme files for details)
3. Add pages to your site with desired components

## Asset Loading

There are several ways to load assets.

1. You can load css and js files into `<head>` section
2. You can load js files at the end of the `<body>` section
3. You can provide custom scripts in the `<head>` section
4. You can provide page specific css and js files

### Asset Loading Modes

There are several modes in which assets can be loaded.

1. Global mode: loads css and js assets in `Edit`, `Preview` and `Live` modes.
    * Navigate to `jContent => Media/files/assets/global` to upload these files. 
2. Preview and live only mode: loads js files in `Preview` and `Live` modes.
    * Navigate to `jContent => Media/files/assets/PreviewAndLive` to upload these files. 
3. Page Composer only mode: loads css files in `Edit` mode only.
    * Navigate to `jContent => Media/files/assets/pageComposerOnly` to upload these files. 

We always load css files first followed by js files. Both css and js files are sorted alphabetically by file name. Note 
that all applicable assets for a given mode and asset type (css or js) are first collected and then sorted.

For Example:

``If you provide A.js and D.js as global js files and B.js and C.js as preview and live mode files, they will be loaded in 
this order: A.js, B.js, C.js and D.js in `Preview` and `Live` modes. Only A.js and D.js will be loaded in `Edit` mode. `` 

### Custom Scripts and Page Specific Files.

Custom scripts and page specific files are loaded in all modes in declared order.

Page specific files are always loaded after all files loaded for specific mode.

* To add site level custom script edit site node in Page Composer and enable `ADD METADATA IN THE <HEAD> TAG`

* To add page specific custom script edit a page in Page Composer and enable `ADD CSS/JS RESOURCES TO THIS PAGE`

* To add page specific file edit a page in Page Composer and enable `ADD CSS/JS RESOURCES TO THIS PAGE`
