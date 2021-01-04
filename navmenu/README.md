# Navmenu
## About
This module provides "extensions" to the `jnt:navMenu` component, allowing to set a parent node to the navmenu, and classes on the
 different html tags generated (the parent tag, the `<ul>`, `<li>` and `<a>` tags).
This is meant to work with only 2 levels of navigation.
 
## How to use it
### Navmenu
* Deploy the module on your site (preferably in a site with an absolute area for the header)
* Add a `jnt:navMenu` component
  * Provide a title, the baseline node, max depth and start level, do not worry/touch the other default fields
* In the layout section, enable view and pick the "custom" one
* Activate the "Custom Nav Menu" mixin/option
* You can then provide custom classes to be applied:
  * **ulclass1**: provide the classes that need to be applied to the `<ul>` tag corresponding to the first level of the navmenu
  * **liclass1**: provide here the classes that will be applied to each first level menu on the `<li>` tags
  * **ulclass2** / **liclass2**: same idea as for the ulclass1 and liclass1, but applied to the second level of navigation
  * **aclass1** / **aclass2** : NOT IMPLEMENTED. The idea was the same as the liclass1/liclass2, but on the `<a>` tag of the page.
  * **openingtag**: provide a wrapper tag to this navmenu
  * **openintagproperties**: provide the classes to the opening tag. The original idea was to be able to provide all the properties (e.g
  . _class="navbar" id="mynavbar"_ but I had issues in the implementation, so you can only list the classes in this PoC)
  * **liclasshaschildren**: provide the class to apply to a `<li>` tag when the page has subpages
  * **ahaschildren**: NOT IMPLEMENTED. Original idea was the same as the liclasshaschildren, but on the `<a>` tag

### Custom class on the `<a>` tag of a specific page
It is possible to specify for a specific page the classes to use on its corresponding `<a>` tag in the navmenu. To do so, open Content
 Editor for the page and activate "Add class to A tag in navmenu". Then provide the classes.

### Example  
The following page structure
```
Home
 |- Test (with a custom a tag)
 |    |-Subpage
 |        |- Third level page
 |- Autre page
```
turns into this html:
```html
<nav class="openintagproperties">
    <ul class="ulclass1">
        <li class="lihaschildren liclass1">
            <a href="/cms/render/default/en/sites/testnav/home/test.html" class="mycustomatag">Test</a>
            <ul class="ulclass2">
                <li class="liclass2">
                    <a href="/cms/render/default/en/sites/testnav/home/test/subpage.html">Subpage</a>
                </li>
            </ul>
        </li>
        <li class="liclass1">
            <a href="/cms/render/default/en/sites/testnav/home/autre-page.html">Autre page</a>
        </li>
    </ul>
</nav>
```
As we only have a depth of 2, the "Third level page" is not displayed in the navmenu.

## Implementation details
* There are two mixins, one to extend the navmenu so one can provide the custom classes for the menu, and one mixin that extends the
 pages, so one can specify the custom class for the `<a>` tag of this page
* I reused one of the `jnt:navMenu` view for the `navMenu.custom.jsp.html`
* To be able to provide the custom `<a>` tag for specific page, I created a `jmix_navMenuItem/html/navMenuItem.menuElement2.groovy.html
` and `jnt_page/html/page.menuElement2.groovy.html`

## Ideas for what's next
* Maybe in Content Editor, it would be interesting to have a rendered preview of this menu (which is currently the case) and the
 possibility to switch to the plain html (like in the "Example" section of this readme) so one can ensure the right classes are well set, or
  easily know what the
  DOM looks
  like to
  adapt the CSS