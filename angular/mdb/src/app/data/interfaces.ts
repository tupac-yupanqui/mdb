export interface LoginData {
    username: string;
    password: string;
}

export interface UserToken {
    token: string;
    type?: string;
    id: number;
    username: string;
    email?: string;
    roles: Array<string>;
}

export interface Artist {
    id?: number;
    name?: string;
}

export interface Album {
    id: number;
    name: string;
    artist?: Artist,
    release?: Date;
    cover?: string;
    coversmall?: string;
    subalbums? : Subalbum[]
}

export interface Subalbum {
    id: number;
    name: string;
}

export interface Titel {
    id: number;
    name: string;
    version: string;
    comment: string;
    artist: Artist;
    tracknr: number;
    length: number;
}

export interface AlbumDetails {
    album: Album;
    titels: TitelList[];
}

export interface TitelList {
    subalbum?: Subalbum;
    list?: Titel[];
}

