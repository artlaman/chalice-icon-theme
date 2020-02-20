#! /bin/bash -ex

pip3 install fontmake
pip3 install git+https://github.com/googlefonts/gftools
brew install ttfautohint
brew tap bramstein/webfonttools
brew install sfnt2woff-zopfli