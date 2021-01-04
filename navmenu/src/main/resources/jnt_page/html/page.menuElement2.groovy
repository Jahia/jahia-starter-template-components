title = currentNode.displayableName
//                    +index+" "+ nbOfChilds+" "+closeUl
description = currentNode.properties['jcr:description']
linkTitle = description ? " title=\"${description.string}\"" : ""

aclass = currentNode.properties['aclass']
customclass = aclass ? " class=\"${aclass.string}\"" : ""

link = currentNode.url

print "<a href=\"${link}\"${linkTitle}${customclass}>${title}</a>"