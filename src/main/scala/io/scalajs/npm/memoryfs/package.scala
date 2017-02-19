package io.scalajs.npm

import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.fs.{FileIOError, Stats}
import io.scalajs.util.PromiseHelper._

import scala.concurrent.Promise
import scala.scalajs.js
import scala.scalajs.js.Array

/**
  * memory-fs package object
  * @author lawrence.daniels@gmail.com
  */
package object memoryfs {

  /**
    * Memory File System Extensions
    * @param fs the given [[MemoryFs memory file system]]
    */
  final implicit class MemoryFSExtensions(val fs: MemoryFs) extends AnyVal {

    @inline
    def existsFuture(path: String): Promise[Boolean] = promiseWithError1[FileIOError, Boolean](fs.exists(path, _))

    @inline
    def mkdirpFuture(path: String): Promise[Unit] = promiseWithError0[FileIOError](fs.mkdirp(path, _))

    @inline
    def readdirFuture(path: String): Promise[Array[String]] =
      promiseWithError1[FileIOError, js.Array[String]](fs.readdir(path, _))

    @inline
    def readFileFuture(path: String, optArg: js.Any = null): Promise[Buffer] =
      promiseWithError1[FileIOError, Buffer](fs.readFile(path, optArg, _))

    @inline
    def rmdirFuture(path: String): Promise[Unit] = promiseWithError0[FileIOError](fs.rmdir(path, _))

    @inline
    def statFuture(path: String): Promise[Stats] = promiseWithError1[FileIOError, Stats](fs.stat(path, _))

    @inline
    def unlinkFuture(path: String): Promise[Unit] = promiseWithError0[FileIOError](fs.unlink(path, _))

    @inline
    def writeFileFuture(path: String, content: String, encoding: String = null): Promise[Unit] =
      promiseWithError0[FileIOError](fs.writeFile(path, content, encoding, _))

    @inline
    def writeFileFuture(path: String, content: Buffer): Promise[Unit] =
      promiseWithError0[FileIOError](fs.writeFile(path, content, _))

  }

}
