import java.io.File

fun main(args: Array<String>) {
    val dir: File

    if (args.isEmpty())
        dir = File(System.getProperty("user.dir"))
    else {
        dir = File(args[0])

        if (!dir.exists()) {
            println("Directory ${dir.toPath().toAbsolutePath().normalize()} does not exist")
            return
        }

        if (!dir.isDirectory) {
            println("${dir.toPath().toAbsolutePath().normalize()} is a file")
            return
        }
    }

    renameFiles(dir, dir)
}

fun renameFiles(dir: File, mainDir: File) {
    val files = dir.listFiles()

    for (file in files) {
        if (file.isDirectory) {
            renameFiles(file, mainDir)
        } else {
            if (file.extension.equals("java", ignoreCase = true)
                    || file.extension.equals("kt", ignoreCase = true)) {
                val newFileName = File("${file.absolutePath}.2019")
                file.renameTo(newFileName)

                println(newFileName.absoluteFile.relativeTo(mainDir).path)
            }
        }
    }
}