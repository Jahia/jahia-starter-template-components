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

We always load css files first followed by js files in each mode. Both css and js files per mode are sorted alphabetically by file name. 

Note: that all applicable assets for a given mode and asset type (css or js) are first collected and then sorted.

Note: the order of mode by increasing priority is global, preview/live, page override, and page composer.

For Example:

``If you provide A.js and D.js as global js files and B.js and C.js as preview and live mode files, they will be loaded in 
this order: A.js, D.js, B.js and C.js in `Preview` and `Live` modes. Only A.js and D.js will be loaded in `Edit` mode. `` 

### Custom Scripts and Page Specific Files.

Custom scripts and page specific files are loaded in all modes in declared order.

Page specific files are always loaded after all files loaded for specific mode.

* To add site level custom script edit site node in Page Composer and enable `ADD METADATA IN THE <HEAD> TAG`

* To add page specific custom script edit a page in Page Composer and enable `ADD CSS/JS RESOURCES TO THIS PAGE`

* To add page specific file edit a page in Page Composer and enable `ADD CSS/JS RESOURCES TO THIS PAGE`

---
## Navigation Menu component

Provides a component that renders HTML for a navigation menu and allows for HTML class and CSS customizations. This component reflects the
page structure of the parent page where the navigation menu component has been added.  For example, if the navigation menu is added in the
header, then it'll reflect the page navigation structure under the home page. If it's added within the body, then it'll reflect the navigation
structure within the given page itself. For simplicity, the HTML structure rendered only allows up to two levels of page hierarchy at any
given time.

As an example, given the following page structure for a navigation menu component added to the header section:

```
* Home
    * Page 1
        * Subpage 1
        * Subpage 2
    * Page 2
        * Subpage 3
            * Subpage 4
    * Page 3

```

The HTML is generated as follows depending on what _Navigation menu type_ option is selected within the navigation menu component
properties:

```
<nav>
    <ul>
        <li>
            <a href="page1.html">Page 1</a>
            <ul>
                <li><a href="subpage1.html">Subpage 1</a></li>
                <li><a href="subpage1.html">Subpage 2</a></li>
            </ul>
        </li>
        <li>
            <a href="page2.html">Page 2</a>
            <ul>
                <li><a href="subpage3.html">Subpage 3</a></li>
            </ul>
        </li>
        <li>
            <a href="page3.html">Page 3</a>
        </li>
    </ul>
</nav>
```

The option _Use `<ul>` and `<li>` tags_ is selected by default and generates HTML structure above. If the option _Use nested `<div>`
tags"_ is selected, then the `<ul>` and `<li>` tags are replaced by `<div>` tags instead. Note that _Subpage 4_ is not included as it is
past the two-level limit of the component.

There is also a toggle option _Include Level 2 pages_ enabled by default. If disabled, then only the first level page hierarchy is
included, and the structure above will render the following HTML:

```
<nav>
    <ul>
        <li><a href="page1.html">Page 1</a></li>
        <li><a href="page2.html">Page 2</a></li>
        <li><a href="page3.html">Page 3</a></li>
    </ul>
</nav>
```

### Navigation menu class customizations

The component allows the user to provide custom classes at any given level of the navigation menu structure within _Customize HTML tags_
section. The following is the mapping of the fields to add specified class names depending on the page level:

| _Customize HTML Tag_ fields                              	| HTML element affected                            	| Notes                                                                                            	|
|--------------------------------------------------------	|--------------------------------------------------	|--------------------------------------------------------------------------------------------------	|
| `<nav>` container tag classes                          	| `nav`                                            	|                                                                                                  	|
| Level 1 list classes                                   	| `nav > ul`                                       	|                                                                                                  	|
| Level 1 item classes                                   	| `nav > ul > li`                                  	|                                                                                                  	|
| Additional classes <br>for Level 1 items with subpages 	| `nav > ul > li`                                  	| only if _Include level 2 pages_are enabled <br>and there are level 2 pages added within the page 	|
| Level 2 list classes                                   	| `nav > ul > li > ul`                             	| only if _Include level 2 pages_ are enabled <br>and there are level 2 pages added                	|
| Level 2 item classes                                   	| `nav > ul > li > ul > li`                        	| only if _Include level 2 pages_ are enabled <br>and there are level 2 pages added                	|
| Current page classes                                   	| `nav > ul > li` or<br> `nav > ul > li > ul > li` 	| depends on which level the current page belongs                                                  	|


