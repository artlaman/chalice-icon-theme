#! /bin/bash -ex

rm -f dist/*.(woff|ttf)

# make ttf
fontmake -g src/glyphs/ChaliceIcons.glyphs -o ttf --output-dir dist

# autohint
statics=$(ls dist/*.ttf)
for file in $statics; do 
    echo "fix DSIG in " ${file}
    gftools fix-dsig --autofix ${file}

    echo "TTFautohint " ${file}
    # autohint with detailed info
    hintedFile=${file/".ttf"/"-hinted.ttf"}
    ttfautohint -I ${file} ${hintedFile} --stem-width-mode nnn --composites --windows-compatibility --symbol
    cp ${hintedFile} ${file}
    rm -rf ${hintedFile}
done

# build woff
ttfs=$(ls dist/*.ttf)
for ttf in $ttfs; do
    sfnt2woff-zopfli $ttf
done
