# lein-datomic-pro

A Leiningen plugin to simplify use of Datomic Pro.

## Disclaimer

This is mainly for my own use, because I got sick of modifying Leiningen deps
to point to the Pro version of Datomic.

## Usage

From this repository, checked out locally, install the plugin to your local
Maven repo:

```sh
lein install
```

## Effects

This plugin will:

1. change lein deps matching `[com.datomic/datomic-free _]` to
   `[com.datomic/datomic-pro 0.9.5344]`.

2. If any Datomic deps are found, it will add the PostgreSQL JDBC driver as a
   dependency.

## License

Copyright © 2016 Benjamin R. Haskell

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
